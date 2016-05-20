package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_post")
public class Post {
    private Integer postId;
    private User author;
    private Timestamp submissionDate;
    private String postTitle;
    private String postContent;

    @Id
    @SequenceGenerator(name="postSequence", sequenceName="t_post_post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSequence")
    @Column(name = "post_id")
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "submission_date")
    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Column(name = "post_title")
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Column(name = "post_content")
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", author=" + author +
                ", submissionDate=" + submissionDate +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
