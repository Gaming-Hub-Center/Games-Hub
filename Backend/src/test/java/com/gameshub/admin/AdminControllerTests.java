package com.gameshub.admin;

import com.gameshub.dto.product.ProductBriefDTO;
import com.gameshub.dto.user.AdminDTO;
import com.gameshub.dto.user.BuyerDTO;
import com.gameshub.dto.user.SellerDTO;
import com.gameshub.service.admin.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdminService adminService;

    @Test
    void testGetAllBuyers() {
        ResponseEntity<List<BuyerDTO>> responseEntity = restTemplate.exchange(
                "/admin/view/buyers", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BuyerDTO>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testDeleteBuyer() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/admin/delete/buyer/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Buyer 1 deleted", responseEntity.getBody());
    }

    @Test
    void testGetSellerPhysicalProducts() {
        ResponseEntity<List<ProductBriefDTO>> responseEntity = restTemplate.exchange(
                "/admin/view/physical/products/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductBriefDTO>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        System.out.println(responseEntity.getBody().size());
    }

    @Test
    void testGetAllSellers() {
        ResponseEntity<List<SellerDTO>> responseEntity = restTemplate.exchange(
                "/admin/view/sellers", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SellerDTO>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testGetAllAdmins() {
        ResponseEntity<List<AdminDTO>> responseEntity = restTemplate.exchange(
                "/admin/view/admins/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AdminDTO>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testDeleteSeller() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/admin/delete/seller/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Seller 1 deleted", responseEntity.getBody());
    }

    @Test
    void testDeleteAdmin() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/admin/delete/admin/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Admin 1 deleted", responseEntity.getBody());
    }

    @Test
    void testDeletePhysicalProduct() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/admin/delete/physical/product/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Physical Product 1 deleted", responseEntity.getBody());
    }

    @Test
    void testDeleteDigitalProduct() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "/admin/delete/digital/product/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Digital Product 1 deleted", responseEntity.getBody());
    }

    @Test
    void testGetSellerDigitalProducts() {
        ResponseEntity<List<ProductBriefDTO>> responseEntity = restTemplate.exchange(
                "/admin/view/digital/products/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductBriefDTO>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}
