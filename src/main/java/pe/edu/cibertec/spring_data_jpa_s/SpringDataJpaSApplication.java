package pe.edu.cibertec.spring_data_jpa_s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.cibertec.spring_data_jpa_s.entity.Customer;
import pe.edu.cibertec.spring_data_jpa_s.entity.Film;
import pe.edu.cibertec.spring_data_jpa_s.repository.CustomerRepository;
import pe.edu.cibertec.spring_data_jpa_s.repository.FilmRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringDataJpaSApplication implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FilmRepository filmRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaSApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * findAll()
         */
//         customerRepository.findAll().forEach(System.out::println);

        /**
         * save()
         */
//        for (int i = 0; i < 10; i++) {
//            Customer customer = new Customer(null, 1, "César", "Santos", "cesar@mail.com", 1, 1, new Date(), new Date());
//            customerRepository.save(customer);
//        }

        /**
         * findById() - orElse + orElseGet
         */
//        Optional<Customer> optional = customerRepository.findById(604);
//        Customer customer = optional.orElseGet(() -> {
//            LocalDate localDate = LocalDate.now();
//            System.out.println("Customer not found " + localDate);
//            return null;
//        });
//        System.out.println(customer);

        /**
         * findById() - orElseThrow
         */
//        Optional<Customer> optional = customerRepository.findById(604);
//        Customer customer = optional.orElseThrow(IllegalArgumentException::new);
//        System.out.println(customer);

        /**
         * isPresent()
         */
//        Optional<Customer> optional = customerRepository.findById(604);
//        if (optional.isPresent()) {
//            System.out.println(optional.get());
//        } else {
//            System.out.println("Customer not found");
//        }

        /**
         * ifPresent()
         */
//        Optional<Customer> optional = customerRepository.findById(604);
//        optional.ifPresent((item) -> {
//            LocalDate today = LocalDate.now();
//            System.out.println("Fecha " + today);
//            System.out.println(item);
//        });

        /**
         * ifPresentOrElse()
         */
//        Optional<Customer> optional = customerRepository.findById(604);
//        optional.ifPresentOrElse((item) -> {
//                    LocalDate today = LocalDate.now();
//                    System.out.println("Fecha " + today);
//                    System.out.println(item);
//                },
//                () -> {
//                    System.out.println("Customer not found :)");
//                });

        /**
         * existsById()
         */
//        if (customerRepository.existsById(604)) {
//            System.out.println("Ejecutar lógica si cliente existe");
//        } else {
//            System.out.println("Ejecutar lógica si cliente NO existe");
//        }

        /**
         * findAll()
         */
//        Iterable<Customer> iterable = customerRepository.findAll();
        // for clasico
//        for (Customer customer : iterable) {
//            System.out.println(customer);
//        }

        //forEach
//        iterable.forEach(System.out::println);

        // forEach - function
//        iterable.forEach((item) -> {
//            System.out.println(item);
//        });

        /**
         * List.copyOf()
         * Convertir de Iterable a List
         */
//        List<Customer> customers = List.copyOf((Collection<Customer>) iterable);

        /**
         * findAllById()
         */
//        Iterable<Integer> ids = List.of(600, 601, 702);
//        Iterable<Customer> iterable = customerRepository.findAllById(ids);
//        iterable.forEach(System.out::println);

        /**
         * deleteById()
         */
//        Integer id = 603;
//        if (customerRepository.existsById(id)) {
//            customerRepository.deleteById(id);
//        } else {
//            System.out.println("Customer with id " + id + " does not exist");
//        }

        /**
         * deleteAllById()
         */
//        List<Integer> ids = List.of(600, 601);
//        ids.stream().filter(customerRepository::existsById).collect(Collectors.toList());
//
//        if (!ids.isEmpty()) {
//            customerRepository.deleteAllById(ids);
//        } else {
//            System.out.println("Ids no existen en BD");
//        }

        /**
         * delete(Entity e)
         */
//        Optional<Customer> optional = customerRepository.findById(613);
//        optional.ifPresentOrElse(
//                (elemento) -> {
//                    customerRepository.delete(elemento);
//                },
//                () -> {
//                    System.out.println("Customer not found");
//                }
//        );

        /**
         * deleteAll()
         */
//        Iterable<Customer> iterable = customerRepository.findAllById(List.of(604, 605, 606, 607, 608, 609, 610, 611, 612, 900));
//        customerRepository.deleteAll(iterable);

        /**
         * findById - map()
         */
//        Optional<Customer> optional = customerRepository.findById(599);
//        String email = optional.map(Customer::getEmail).orElseGet(
//                () -> {
//                    System.out.println("Customer not found");
//                    return "No-Email";
//                }
//        );
//        System.out.println(email);

        /**
         * findAll - Caching
         */
        System.out.println("-------------------------------");
        System.out.println("findAll() - 1ra llamada MySQL");
        System.out.println("-------------------------------");
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach((film) -> {
            String message = String.format("%s:%s;", film.getFilm_id(), film.getTitle());
            System.out.print(message);
        });

        /**
         * findAll - Caching
         */
        System.out.println(" ");
        System.out.println("-------------------------------");
        System.out.println("findAll() - 2da llamada Cache");
        System.out.println("-------------------------------");
        Iterable<Film> iterable2 = filmRepository.findAll();
        iterable2.forEach((film) -> {
            String message = String.format("%s:%s;", film.getFilm_id(), film.getTitle());
            System.out.print(message);
        });

    }

}
