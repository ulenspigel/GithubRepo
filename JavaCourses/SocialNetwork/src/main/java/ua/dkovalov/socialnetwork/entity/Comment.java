package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;
import java.sql.Date;

// TODO: common stereotype-table for comments and posts with common fields that allows provide self reference
// and make possible to link comments to posts and other comments

@Entity
@Table(name = "t_comment")
public class Comment {
    private Integer commentId;
    private Date commentDate;
    private User author;
    private Post parent;
    private String commentText;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Column(name = "comment_date")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    @Column(name = "comment_text")
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentDate=" + commentDate +
                ", author Id=" + author.getUserId() +
                ", author nickname=" + author.getNickname() +
                ", post Id=" + parent.getPostId() +
                ", post title=" + parent.getPostTitle() +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
