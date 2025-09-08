// package com.example.kakeibo.service;

// import com.example.kakeibo.Entity.User;
// import com.example.kakeibo.Repository.UserRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;

// class UsersServiceTest {

//     private UserRepository usersRepository;
//     private UsersService usersService;

//     @BeforeEach
//     void setUp() {
//         // モックのRepositoryを作成
//         usersRepository = Mockito.mock(UserRepository.class);
//         usersService = new UsersService(usersRepository);
//     }

//     @Test
//     void testFindAll() {
//         // given
//         List<User> mockUsers = Arrays.asList(
//                 new User(1, "Taro"),
//                 new User(2, "Hanako")
//         );
//         when(usersRepository.findAll()).thenReturn(mockUsers);

//         // when
//         List<User> users = usersService.findAll();

//         // then
//         assertThat(users).hasSize(2);
//         assertThat(users.get(0).getName()).isEqualTo("Taro");
//         verify(usersRepository, times(1)).findAll();
//     }

//     @Test
//     void testFindById() {
//         // given
//         User taro = new User(1, "Taro");
//         when(usersRepository.findById(1)).thenReturn(Optional.of(taro));

//         // when
//         Optional<User> result = usersService.findById(1);

//         // then
//         assertThat(result).isPresent();
//         assertThat(result.get().getName()).isEqualTo("Taro");
//         verify(usersRepository, times(1)).findById(1);
//     }
// }
