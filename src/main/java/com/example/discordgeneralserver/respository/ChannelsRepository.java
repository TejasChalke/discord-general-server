package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.Channels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelsRepository extends JpaRepository<Channels, Integer> {
    Channels findFirstByCreatorIdAndName(Integer creatorId, String name);

    @Query(value = "Select c.id, c.creator_id, c.name " +
            "From channels As c " +
            "Where c.id In (" +
            " Select m.channel_id " +
            "    From members as m " +
            "    Where m.user_id = ?1" +
            ")", nativeQuery = true)
    List<Object> findChannelsByUserId(Integer userId);

    @Query(value = "Select c.id, c.creator_id, c.name From channels As c " +
            "Where c.creator_id <> ?1 " +
            "And c.name Like Concat(?2, '%')", nativeQuery = true)
    List<Object> findChannelByNameAndUserId(Integer userId, String name);
}
