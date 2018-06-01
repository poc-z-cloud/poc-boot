package poc.spring.boot.domain.model;

import java.math.BigDecimal;
 
public class Product extends AbstractDomainClass{
    private String description;
    private String imageUrl;
    private BigDecimal price;
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getImageUrl() {
        return imageUrl;
    }
 
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
 
    public BigDecimal getPrice() {
        return price;
    }
 
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}