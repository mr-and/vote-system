package com.votesystem.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "votes")
public class Vote extends AbstractBaseIdEntity implements Serializable {

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonBackReference(value = "vote")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "votes")
    private User user;

    public Vote(LocalDate localDate, User user, Restaurant restaurant) {
        this(null, localDate, user, restaurant);
    }

    public Vote(Integer id, LocalDate localDate, User user, Restaurant restaurant) {
        super(id);
        this.date = localDate;
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

}
