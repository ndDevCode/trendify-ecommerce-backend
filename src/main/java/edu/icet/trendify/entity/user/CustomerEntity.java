package edu.icet.trendify.entity.user;

import edu.icet.trendify.entity.order.OrderEntity;
import edu.icet.trendify.entity.product.ProductReviewEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder

@Entity
@Table(name = "customer")
public class CustomerEntity extends UserSuper {
    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<ContactEntity> contactList;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<CustomerAddressEntity> addressList;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<OrderEntity> orderList;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<ProductReviewEntity> reviewList;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CustomerEntity that = (CustomerEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
