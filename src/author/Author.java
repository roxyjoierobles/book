package author;


public class Author {
    public String name;
    public String role;
    public Double rating;
    public String img;
    public String link;
    public Integer ratingsCount;

    public Author() {
        this.name = "";
        this.role = "";
    }

    // constructor with given name
    public Author(String name) {
        this.name = name;
    }

    // constructor with given name and role
    public Author(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // returns authors name
    public String getName() {
        return this.name;
    }

    // returns role of author
    public String getRole() {
        return this.role;
    }

    // returns authors rating count
    public Integer getRatingsCount() {
        return this.ratingsCount;
    }

    // returns authors average rating
    public Double getRating() {
        return this.rating;
    }

    // returns image of author
    public String getImg() {
        return this.img;
    }

    // returns goodreads link of author
    public String getLink() {
        return this.link;
    }

    // sets name of author
    public void setName(String name) {
        this.name = name;
    }

    // sets role of author
    public void setRole(String role) {
        this.role = role;
    }

    // sets avg rating
    public void setRating(Double rating) {
        this.rating = rating;
    }

    // sets ratings count
    public void setRatingsCount(Integer count) {
        this.ratingsCount = count;
    }

    // sets authors image
    public void setImg(String img) {
        this.img = img;
    }

    // sets authors goodreads page link
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        return (name.hashCode() * 31) + (role.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Author other = (Author) o;
        if (name == null && other.name != null) {
            return false;
        }
        if (role != other.role) {
            return false;
        }
        return true;
    }


}
