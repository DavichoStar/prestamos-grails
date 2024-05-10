import com.monaco.prestamos.UserPasswordEncoderListener
import com.monaco.prestamos.CustomUserDetailsService

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
