package gustavovaldes.prueba.service;

import gustavovaldes.prueba.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public User save(User user) {
        // Validar que los campos no estén vacíos o nulos
        if (user.getId() <= 0) {
            throw new IllegalArgumentException("El id debe ser un número entero positivo");
        }
        
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }

        // Validar que el id sea único
        boolean idExists = users.stream()
                .anyMatch(u -> u.getId() == user.getId());
        
        if (idExists) {
            throw new IllegalArgumentException("El id ya está registrado");
        }

        // Validar que el email sea único
        boolean emailExists = users.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        
        if (emailExists) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        users.add(user);
        return user;
    }
}