package com.pssdev.pss.entity;

import org.springframework.lang.Nullable;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class OrderForm {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private int type;

  @Nullable
  @ManyToOne(targetEntity = Employee.class)
  @JoinColumn(name = "creatUser_id")
  private Employee creatUser;

  @Nullable
  @ManyToOne(targetEntity = Employee.class)
  @JoinColumn(name = "verifyUser_id")
  private Employee verifyUser;
  private Date createDate;

  @Nullable
  @JoinColumn(name = "branch_id")
  @ManyToOne(targetEntity = Branch.class)
  private Branch branch;

  @Nullable
  @ManyToOne(targetEntity = Company.class)
  @JoinColumn(name = "company_id")
  private Company company;

  @Nullable
  @ManyToOne(targetEntity = Employee.class)
  @JoinColumn(name = "employee_id")
  private Employee employee; // Todo replace user;

  @Nullable
  @ManyToOne(targetEntity = Department.class)
  @JoinColumn(name = "department_id")
  private Department department;

  @Nullable
  @ManyToOne(targetEntity = Depot.class)
  @JoinColumn(name = "depot_id")
  private Depot depot;
  private String summary;

  @Nullable
  @JoinColumn(name = "account_id")
  @ManyToOne(targetEntity = Account.class)
  private Account account;
  private double money;
  private double wipe;

  @Nullable
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @ManyToMany(targetEntity = OrderFormProduct.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "order_product_id")
  private Set<OrderFormProduct> products;
  private int status;

  @Transient
  private int actionType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Employee getCreatUser() {
    return creatUser;
  }

  public void setCreatUser(Employee creatUser) {
    this.creatUser = creatUser;
  }

  public Employee getVerifyUser() {
    return verifyUser;
  }

  public void setVerifyUser(Employee verifyUser) {
    this.verifyUser = verifyUser;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Depot getDepot() {
    return depot;
  }

  public void setDepot(Depot depot) {
    this.depot = depot;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  public double getWipe() {
    return wipe;
  }

  public void setWipe(double wipe) {
    this.wipe = wipe;
  }

  public int getActionType() {
    return actionType;
  }

  public void setActionType(int actionType) {
    this.actionType = actionType;
  }

  public Set<OrderFormProduct> getProducts() {
    return products;
  }

  public void setProducts(Set<OrderFormProduct> products) {
    this.products = products;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
