package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class OrderForm {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private int type;
   @ManyToOne(targetEntity = User.class)
   @JoinColumn(name = "creatUser_id")
   private User creatUser;
   @ManyToOne(targetEntity = User.class)
   @JoinColumn(name = "verifyUser_id")
   private User verifyUser;
   private Date createDate;
   @JoinColumn(name = "branch_id")
   @ManyToOne(targetEntity = Branch.class)
   private Branch branch;
   @ManyToOne(targetEntity = Company.class)
   @JoinColumn(name = "company_id")
   private Company company;
   @ManyToOne(targetEntity = User.class)
   @JoinColumn(name = "employee_id")
   private User employee; // Todo replace user;
   @ManyToOne(targetEntity = Department.class)
   @JoinColumn(name = "department_id")
   private Department department;
   @ManyToOne(targetEntity = Depot.class)
   @JoinColumn(name = "depot_id")
   private Depot depot;
   private String summary;
   @JoinColumn(name = "account_id")
   @ManyToOne(targetEntity = Account.class)
   private Account account;
   private double money;
   private double wipe;
   private int actionType;
   @ManyToMany(targetEntity = Product.class)
   @JoinColumn(name = "product_id")
   private Set<Product> products;
   private int status;

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

   public User getCreatUser() {
      return creatUser;
   }

   public void setCreatUser(User creatUser) {
      this.creatUser = creatUser;
   }

   public User getVerifyUser() {
      return verifyUser;
   }

   public void setVerifyUser(User verifyUser) {
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

   public User getEmployee() {
      return employee;
   }

   public void setEmployee(User employee) {
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

   public Set<Product> getProducts() {
      return products;
   }

   public void setProducts(Set<Product> products) {
      this.products = products;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }
}
