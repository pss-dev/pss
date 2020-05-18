package com.pssdev.pss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

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
   private Integer fatherId;

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

   public Integer getFatherId() {
      return fatherId;
   }

   public void setFatherId(Integer fatherId) {
      this.fatherId = fatherId;
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
              Objects.equals(fatherId, company.fatherId);
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
              ", fatherId='" + fatherId + '\'' +
              '}';
   }
}
