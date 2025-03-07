package com.example.demo.model;

import com.example.demo.contract.Genre;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

@Entity
public class Category {
    private int categoryId;
    private String name;
    private Timestamp lastUpdate;
    private Collection<FilmCategory> filmCategories;

    public Category(int categoryId, String name, Timestamp lastUpdate) {
        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    public Category() { }

    public static Category fromGenre(Genre genre) {
        return new Category(genre.getId(), genre.getName(), Timestamp.from(Instant.now()));
    }

    @Id
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(category.lastUpdate) : category.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Collection<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }
}
