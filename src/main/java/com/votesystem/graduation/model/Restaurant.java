package com.votesystem.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "name_address_unique_idx")})
public class Restaurant extends AbstractBaseIdEntity implements Serializable {

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<Vote> votes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant", orphanRemoval = true)
    @JsonIgnore
    private List<Menu> menus;

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
