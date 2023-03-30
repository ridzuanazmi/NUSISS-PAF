package nusiss.paf.day27lectureworkshop.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tvshows")
public class Shows {

    @Field("_id")
    private String id1;

    @Field("name")
    private String name;

    @Field("id")
    private int id;

    private String language;
    private List<String> genres;
    private String status;

    public Shows() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
/* 
 * {
    "_id" : ObjectId("6421212cf9c7e3d28fc2b7d7"),
    "id" : NumberInt(2),
    "url" : "http://www.tvmaze.com/shows/2/person-of-interest",
    "name" : "Person of Interest",
    "type" : "Scripted",
    "language" : "English",
    "genres" : [
        "Drama",
        "Action",
        "Crime"
    ],
    "status" : "Ended",
    "runtime" : NumberInt(60),
    "premiered" : "2011-09-22",
    "officialSite" : "http://www.cbs.com/shows/person_of_interest/",
    "schedule" : {
        "time" : "22:00",
        "days" : [
            "Tuesday"
        ]
    },
    "rating" : {
        "average" : NumberInt(9)
    },
    "weight" : NumberInt(96),
    "network" : {
        "id" : NumberInt(2),
        "name" : "CBS",
        "country" : {
            "name" : "United States",
            "code" : "US",
            "timezone" : "America/New_York"
        }
    },
    "webChannel" : null,
    "externals" : {
        "tvrage" : NumberInt(28376),
        "thetvdb" : NumberInt(248742),
        "imdb" : "tt1839578"
    },
    "image" : {
        "medium" : "http://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg",
        "original" : "http://static.tvmaze.com/uploads/images/original_untouched/163/407679.jpg"
    },
    "summary" : "<p>You are being watched. The government has a secret system, a machine that spies on you every hour of every day. I know because I built it. I designed the Machine to detect acts of terror but it sees everything. Violent crimes involving ordinary people. People like you. Crimes the government considered \"irrelevant\". They wouldn't act so I decided I would. But I needed a partner. Someone with the skills to intervene. Hunted by the authorities, we work in secret. You'll never find us. But victim or perpetrator, if your number is up, we'll find you.</p>",
    "updated" : NumberInt(1535507028),
    "_links" : {
        "self" : {
            "href" : "http://api.tvmaze.com/shows/2"
        },
        "previousepisode" : {
            "href" : "http://api.tvmaze.com/episodes/659372"
        }
    }
}
 */