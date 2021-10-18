import com.example.listandadaptor.model.GouvSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/")
    fun getPlaces(@Query("q") address: String): Call<GouvSearchResult>

}
