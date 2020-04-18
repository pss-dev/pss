package com.pssdev.pss.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "t_dept")
public class Department implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column
   private String name;
   @Column
   private String initials;

   @ManyToOne
   @JoinColumn(name = "parent_id")
   private Department parent;

   @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private Set<Department> children = new HashSet<>();

   public Department() {
   }

   public Department(Integer id) {
      this.id = id;
   }

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

   public Set<Department> getChildren() {
      return children;
   }

   public void setChildren(Set<Department> children) {
      this.children = children;
   }

   public Department getParent() {
      return parent;
   }

   public void setParent(Department parent) {
      this.parent = parent;
   }

   @Override
   public String toString() {
      return "Department{" +
         "id=" + id +
         ", name='" + name + '\'' +
         ", initials='" + initials + '\'' +
         ", parent=" + (parent != null ? parent.id : null) +
         ", children=[" + (children != null
            ? children.stream().map(d -> d.id + "").collect(Collectors.joining(","))
            : null)
         + "]}";
   }
}
