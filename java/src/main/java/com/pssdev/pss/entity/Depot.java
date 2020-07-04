package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

@Entity()
@ApiModel("仓库信息")
@JsonIgnoreProperties
public class Depot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;

  @Nullable
  @ManyToOne(targetEntity = Branch.class)
  private Branch branch;

  private String initials;

  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = Depot.class)
  private Depot parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Depot> children = new HashSet<>();
  @OneToMany(targetEntity = DepotItem.class, mappedBy = "depot", fetch = FetchType.EAGER)
  @JsonIgnore
  private List<DepotItem> depotItems;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public Depot getParent() {
    return parent;
  }

  public void setParent(Depot parent) {
    this.parent = parent;
  }

  public Set<Depot> getChildren() {
    return children;
  }

  public void setChildren(Set<Depot> children) {
    this.children = children;
  }

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
  }

  public List<DepotItem> getDepotItems() {
    return depotItems;
  }

  public void setDepotItems(List<DepotItem> depotItems) {
    this.depotItems = depotItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Depot depot = (Depot) o;

    return Objects.equals(id, depot.id) && Objects.equals(name, depot.name) && Objects.equals(initials, depot.initials)
        && Objects.equals(branch, depot.branch) && Objects.equals(parent, depot.parent);
  }

  @Override
  public String toString() {
    return "Company{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", branch='"
        + branch + '\'' + ", parent='" + parent.getName() + '\'' + '}';
  }
}
