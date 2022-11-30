package dietplaner.example.dietplaner.alergen.controller;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.models.UpdateAlergenListForUserDTO;
import dietplaner.example.dietplaner.alergen.repository.AlergenRepository;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AlergenControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlergenRepository alergenRepository;
    @Autowired
    private ProductRepository productRepository;



    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
        productRepository.deleteAll();
        alergenRepository.deleteAll();
    }


    @Test
    void return_OK_200_http_status_addAlergen() throws Exception {

        String jsonBody = new JSONObject()
                .put("name", "ciastko2")
                .toString();


        mockMvc.perform(post("/api/alergen/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void return_OK_200_http_status_getAllAlergens() throws Exception{
        Alergen banany= new Alergen("banany");
        Alergen truskawki=new Alergen("truskawki");
        alergenRepository.save(banany);
        alergenRepository.save(truskawki);

        mockMvc.perform(get("/api/alergen/getall"))
                .andExpect(status().isOk());




    }

    @Test
    void return_OK_200_http_status_and_set_alergen_to_user() throws Exception{
        Alergen alergen= alergenRepository.save(new Alergen("truskawki"));
        DefaultUser user= userRepository.save(new DefaultUser("Login1","password1","name1","surname1","email1"));

        UpdateAlergenListForUserDTO updateAlergenListForUserDTO= new UpdateAlergenListForUserDTO(user.getUserId(),alergen.getAlergenId());
        String jsonBody = new JSONObject()
                .put("userId", user.getUserId())
                .put("alergenId",alergen.getAlergenId())
                .toString();

        mockMvc.perform(patch("/api/alergen/setAlergenToUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }








  @Test
    void return_OK_200_http_status_getAlergenOfUser() throws Exception{
        Alergen czekolada=  alergenRepository.save(new Alergen("czekolada"));
        Alergen fruktoza= alergenRepository.save(new Alergen("fruktoza"));

        List<Alergen> alergensForUser1= new ArrayList<>();
        alergensForUser1.add(czekolada);
        alergensForUser1.add(fruktoza);


        DefaultUser user1= new DefaultUser("Login1","password1","name1","surname1","email1");
        DefaultUser user1WithId=userRepository.save(user1);

        user1WithId.setAlergens(alergensForUser1);

        userRepository.save(user1WithId);

        List<DefaultUser> users = userRepository.findAll();

        mockMvc.perform(get("/api/alergen/getAlergensOfUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user1WithId.getUserId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void return_OK_200_http_status_removeAlergenFromUser() throws Exception{
        Alergen czekolada=  alergenRepository.save(new Alergen("czekolada"));
        Alergen fruktoza= alergenRepository.save(new Alergen("fruktoza"));

        List<Alergen> alergensForUser1= new ArrayList<>();
        alergensForUser1.add(czekolada);
        alergensForUser1.add(fruktoza);




        DefaultUser user1= new DefaultUser("Login1","password1","name1","surname1","email1");
        DefaultUser user1WithId=userRepository.save(user1);

        user1WithId.setAlergens(alergensForUser1);

        userRepository.save(user1WithId);
        UpdateAlergenListForUserDTO dto= new UpdateAlergenListForUserDTO(user1.getUserId(), czekolada.getAlergenId());

        String jsonBody = new JSONObject()
                .put("userId", user1WithId.getUserId())
                .put("alergenId",czekolada.getAlergenId())
                .toString();

        mockMvc.perform(patch("/api/alergen/removeAlergenFromUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void return_OK_200_http_status_setAlergenToProduct() throws Exception{
        Alergen alergen=  alergenRepository.save(new Alergen("alergen"));
        Product product = productRepository.save(new Product("produkt",10L,10L,10L,10L));

        String jsonBody = new JSONObject()
                .put("productId", product.getId())
                .put("alergenId", alergen.getAlergenId())
                .toString();

        mockMvc.perform(patch("/api/alergen/setAlergenToProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void return_OK_200_http_status_removeAlergenFromProduct() throws Exception{

        Alergen alergen=  alergenRepository.save(new Alergen("alergen"));
        Product product = productRepository.save(new Product("produkt",10L,10L,10L,10L));

        List<Alergen> alergensForProduct= new ArrayList<>();
        alergensForProduct.add(alergen);

        product.setAlergens(alergensForProduct);
        productRepository.save(product);

        String jsonBody = new JSONObject()
                .put("productId", product.getId())
                .put("alergenId", alergen.getAlergenId())
                .toString();

        mockMvc.perform(patch("/api/alergen/removeAlergenFromProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}