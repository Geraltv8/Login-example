package com.utngoya.login.restClient

import com.utngoya.login.login.model.LoginRequest
import com.utngoya.login.login.model.LoginResponse
import com.utngoya.login.courses.model.CourseResponse
import com.utngoya.login.students.model.StudentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CursosApi {
    @POST("login")
    fun postLogin(@Body request: LoginRequest ): Call<LoginResponse>

    // Obtener lista de cursos
    @GET("courses")
    fun getCourses(@Header("Authorization") token: String): Call<List<CourseResponse>>

    // Obtener lista de alumnos por curso
     @GET("courses/{course_id}/students")
     fun getStudentsByCourse(
         @Header("Authorization") token: String,
        @Path("course_id") courseId: Int
    ): Call<List<StudentResponse>>

}