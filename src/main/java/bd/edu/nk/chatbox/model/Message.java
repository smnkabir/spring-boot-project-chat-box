package bd.edu.nk.chatbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"sender","msg"})

@Entity
public class Message {
    @Id
    @GeneratedValue
    private int msgId;

    private String sender;
    private String msg;
}
