package com.sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
@XmlRootElement
public class Server implements Persistable<Integer>
{

    @Id private Integer id;
    private String name;
    private String description;

    @Transient private boolean newServer;

    public Server(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean isNew() {
        return newServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return id.equals(server.id) &&
                name.equals(server.name) &&
                description.equals(server.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
