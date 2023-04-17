// package ai.vishal.fms.config;

// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// @EnableWebSecurity
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//     @Autowired
//     UserDetailsService userDetailsService;

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService);
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//                 .antMatchers("/admin").hasRole("ADMIN")
//                 .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                 .antMatchers("/sendmessage").hasAnyRole("USER", "ADMIN")
//                 .antMatchers("/sendmessage/*/*").hasAnyRole("USER", "ADMIN")
//                 .antMatchers("/webhooks").permitAll()
//                 .antMatchers("/customer").hasAnyRole("USER", "ADMIN")
//                 .antMatchers("/customer/*").hasAnyRole("USER", "ADMIN")
//                 .antMatchers("/").permitAll()
//                 .and().httpBasic().and().csrf().disable();
//     }

//     @Bean
//     public PasswordEncoder getPasswordEncoder() {
//         return NoOpPasswordEncoder.getInstance();
//     }
// }
