package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.DepotDao;
import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepotServiceImpl implements DepotService {
  @Autowired
  private DepotDao DepotDao;

  @Override
  @Transactional
  public void insertDepot(Depot Depot) throws Exception {
    if (!StringUtils.isEmpty(Depot.getFatherId())) {
      Depot father = DepotDao.getDepot(Depot.getFatherId());

      if (father == null) {
        throw new Exception("找不所属仓库");
      }
    }

    if (DepotDao.getDepot(Depot.getId()) != null) {
      throw new Exception("仓库已经存在");
    }

    DepotDao.insertDepot(Depot);
  }

  @Override
  @Transactional
  public void deleteDepot(Integer id) {
    DepotDao.deleteDepot(id);
  }

  @Override
  @Transactional
  public void updateDepot(Depot depot) throws Exception {
    Depot oldDepot = DepotDao.getDepot(depot.getId());

    if (oldDepot == null) {
      throw new Exception("仓库不存在");
    } else {
      oldDepot.setName(depot.getName());
      oldDepot.setBranch(depot.getBranch());
      oldDepot.setInitials(depot.getInitials());
      DepotDao.modifyDepot(oldDepot);
    }
  }

  @Override
  @Transactional
  public List<Depot> getDepots(Integer fatherId) {
    return fatherId == null ? this.getDepots() : DepotDao.getDepots(fatherId);
  }

  @Override
  @Transactional
  public List<Depot> getDepots() {
    return DepotDao.getDepots();
  }
}
