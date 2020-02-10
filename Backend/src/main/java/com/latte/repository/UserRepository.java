package com.latte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latte.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Modifying
	@Query(value = "UPDATE User u SET u.phone = :phone WHERE u.id = :id", nativeQuery = false)
	Integer updateUserPhone(@Param("phone") String phone, @Param("id") Long id);
	// withdrawal
}

/*
 * JPA는 메소드의 이름만으로 원하는 쿼리를 실행하는 방법을 제공합니다. 이때 쿼리는 select에만 해당합니다. 쿼리 메소드는 다음과 같은
 * 단어들로 시작합니다. find...By... read...By... query...By... get...By... count...By...
 * 예를 들어, find...By...을 사용하면 find 뒤에 엔티티 타입을 지정합니다. Board 클래스라면 findBoardBy...이
 * 됩니다. 만약 타입을 지정하지 않으면, 현재 실행하는 Repository의 타입 정보를 기준으로 동작합니다. 여기선
 * JpaRepository<Board, Long>의 Board 타입이 되겠죠👍? By 뒤쪽에는 컬럼명을 이용하여 구성합니다. 예를 들어
 * username 컬럼을 이용해 검색하려면 findBoardByUsername이 됩니다. 쿼리 메소드의 리턴 타입은 Page<T>,
 * Slice<T>, List<T>와 같은 Collection<T> 형태가 됩니다. 쿼리 메소드 작성 방법은 여기서 확인 가능합니다.
 * https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#
 * jpa.query-methods.query-creation
 */