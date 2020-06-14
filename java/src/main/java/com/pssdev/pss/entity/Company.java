package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String name;
   private String initials;
   private String address;
   private String contactPerson;
   private String contactPhone;
   private int type;
   @JoinColumn(name = "father_id")
   @ManyToOne(targetEntity = Company.class)
   private Company parent;
   @JoinColumn(name = "ather_id")
   @OneToMany(targetEntity = Company.class, fetch = FetchType.EAGER)
   private Set<Company> children;

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

   public String getInitials() {
      return initials;
   }

   public void setInitials(String initials) {
      this.initials = initials;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getContactPerson() {
      return contactPerson;
   }

   public void setContactPerson(String contactPerson) {
      this.contactPerson = contactPerson;
   }

   public String getContactPhone() {
      return contactPhone;
   }

   public void setContactPhone(String contactPhone) {
      this.contactPhone = contactPhone;
   }

   public int getType() {
      return type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public Company getParent() {
      return parent;
   }

   public void setParent(Company parent) {
      this.parent = parent;
   }

   @JsonIgnore
   public Set<Company> getChildren() {
      return children;
   }

   public void setChildren(Set<Company> children) {
      this.children = children;
   }

   public boolean isHaveChildren() {
      return this.children == null || this.children.size() == 0 ? false : true;
   }

   @Override
   public boolean equals(Object o) {
      if(this == o) {
         return true;
      }

      if(o == null || getClass() != o.getClass()) {
         return false;
      }

      Company company = (Company) o;

      return Objects.equals(id, company.id) &&
              Objects.equals(name, company.name) &&
              Objects.equals(initials, company.initials) &&
              Objects.equals(address, company.address) &&
              Objects.equals(contactPerson, company.contactPerson) &&
              Objects.equals(contactPhone, company.contactPhone) &&
              Objects.equals(type, company.type) &&
              Objects.equals(parent, company.parent);
   }

   @Override
   public String toString() {
      return "Company{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", initials='" + initials + '\'' +
              ", address='" + address + '\'' +
              ", contactPerson='" + contactPerson + '\'' +
              ", contactPhone='" + contactPhone + '\'' +
              ", type=" + type +
              ", fatherId='" + getParent() + '\'' +
              '}';
   }
}
