package com.bnc.main.product.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ProductTest {

    final static Product product = new Product("옷", 123, "구찌");

    @Test
    void 상품_생성_성공() {
        assertThat(product.getName()).isEqualTo("옷");
        assertThat(product.getPrice()).isEqualTo(123);
        assertThat(product.getBrand()).isEqualTo("구찌");
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.CREATED);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 상품_이름이_null이면_실패(String name){
        assertThatIllegalArgumentException().isThrownBy(() -> new Product(name, 123, "구찌"));
    }

    @Test
    void 상품_이름이_공백이면_실패(){
        String name = "    ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Product(name, 123, "구찌"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 상품_브랜드가_null이면_실패(String brand){
        assertThatIllegalArgumentException().isThrownBy(() -> new Product("옷", 123, brand));
    }

    @Test
    void 상품_브랜드가_공백이면_실패(){
        String brand = "    ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Product("옷", 123, brand));
    }

    @Test
    void 상품_변경_성공(){
        final Product product = new Product("옷",123,"구찌");

        product.change("가방",1233,"디올");

        assertThat(product).isEqualTo(new Product("가방", 1233, "디올"));
    }

    @Test
    void 상품_삭제_성공() {
        final Product product = new Product("옷", 123, "구찌");

        product.delete();

        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.DELETED);
    }
}
