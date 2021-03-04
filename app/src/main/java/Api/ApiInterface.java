package Api;

import Model.DeleteBukuResponse;
import Model.DetailBukuResponse;
import Model.ListBukuResponse;
import Model.LoginResponse;
import Model.RegisterResponse;
import Model.UpdateBukuResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("buku")
    Call<ListBukuResponse>getListBuku();

    @DELETE("buku")
    Call<DeleteBukuResponse>deleteBuku(
            @Query("id") int id
    );

    @FormUrlEncoded
    @PUT("buku")
    Call<UpdateBukuResponse>updateBuku(
            @Field("id") int id,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("harga") String harga,
            @Field("stok") String stok
    );

    @Multipart
    @POST("buku")
    Call<DetailBukuResponse>createBuku(
            @Part("judul")RequestBody judul,
            @Part("deskripsi")RequestBody deskripsi,
            @Part MultipartBody.Part gambar,
            @Part("harga")RequestBody harga,
            @Part("stok")RequestBody stok
            );

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse>registerUser(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") String role
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse>loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}
