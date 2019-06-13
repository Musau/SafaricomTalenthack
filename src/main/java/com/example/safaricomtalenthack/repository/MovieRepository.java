package com.example.safaricomtalenthack.repository;


import com.example.safaricomtalenthack.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel,Long> {

    @Query(value = "SELECT * FROM movies WHERE watched_flag=?",nativeQuery = true)
    List<MovieModel> getWatchedMovies(int watched_flag);
}
