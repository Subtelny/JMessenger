package pl.janda.jmessenger.application.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.janda.jmessenger.infrastructure.converter.LocalDateTimeAttributeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageProjection {

    @Id
    private String messageId;

    private String roomId;

    private String userId;

    private String text;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

}