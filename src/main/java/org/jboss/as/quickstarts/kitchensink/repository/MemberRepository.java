package org.jboss.as.quickstarts.kitchensink.repository;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    // Standard CRUD operations are inherited from MongoRepository

    // Custom query method for name search (case-insensitive)
    List<Member> findByNameContainingIgnoreCase(String name);

    // Email uniqueness check
    boolean existsByEmail(String email);

    // Phone number uniqueness check
    boolean existsByPhoneNumber(String phoneNumber);

    // Complex query example with regex
    @Query("{ 'email' : { $regex: ?0, $options: 'i' } }")
    List<Member> findByEmailPattern(String emailPattern);

    // Projection query for performance
    @Query(value = "{}", fields = "{ 'name' : 1, 'email' : 1 }")
    List<Member> findAllNamesAndEmails();

    // Find by exact email match
    Optional<Member> findByEmail(String email);

    // Find by phone number
    Optional<Member> findByPhoneNumber(String phoneNumber);

    @Query(value = "{}", sort = "{ 'name' : 1 }")
    List<Member> findAllOrderedByName();

}
