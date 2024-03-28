package com.keremsalur.blogapp.model;

import java.util.Arrays;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String title;

    @Column(columnDefinition = "bytea")
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private BlogUser author;

    public Post(String id, String title, byte[] content, BlogUser author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public BlogUser getAuthor() {
        return author;
    }

    public void setAuthor(BlogUser author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + Arrays.hashCode(content);
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (!Arrays.equals(content, other.content))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        return true;
    }

    


}
