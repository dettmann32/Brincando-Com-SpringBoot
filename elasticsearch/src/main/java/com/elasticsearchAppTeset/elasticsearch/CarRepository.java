package com.elasticsearchAppTeset.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.elasticsearchAppTeset.elasticsearch.car;

@Repository
interface CarRepository extends ElasticsearchRepository<car, String> {

    // Match query para texto analisado (busca por palavra)
    @Query("""
    {
      "match": {
        "name": {
          "query": "?0"
        }
      }
    }
    """)
    List<car> searchByNameMatch(String name);

    // Wildcard query (busca por parte do texto, como LIKE)
    @Query("""
    {
      "wildcard": {
        "color": {
          "value": "*?0*"
        }
      }
    }
    """)
    List<car> searchByColorWildcard(String color);

    // Combinação com bool + match + wildcard
    @Query("""
    {
      "bool": {
        "must": [
          {
            "match": {
              "name": {
                "query": "?0"
              }
            }
          },
          {
            "wildcard": {
              "color": {
                "value": "*?1*"
              }
            }
          }
        ]
      }
    }
    """)
    List<car> searchByNameAndColorAdvanced(String name, String color);
}