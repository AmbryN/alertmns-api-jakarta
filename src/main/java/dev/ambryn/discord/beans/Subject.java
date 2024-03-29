package dev.ambryn.discord.beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Subject")
public abstract class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id", nullable = false)
    protected Long id;

    @Column(name = "sub_sent_at", nullable = false)
    protected LocalDateTime sentAt;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sub_channel", nullable = false)
    protected Channel channel;

    public Subject() {
        this.sentAt = LocalDateTime.now();
    }

    public Subject(Channel channel) {
        this.sentAt = LocalDateTime.now();
        this.channel = channel;
    }
}
