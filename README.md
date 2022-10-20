
########################################################################################################################

User Service

@Autowired userRespository, tokenService

Generating a Token

saveUser(){ savedUser = userRepository.save(User)

// creating a token 
    return  tokenService.createToken(savedUser.getId())
}

// token Service createToken(ObjectId userId){

TOKEN_SECRET = “sdffdsafdkjkasjffdjsalkf”
Algorithm // jwt => jwt.HMAC256(TOKEN_SECRET)

// Generation of JSON Web Token by two claims with userId and Current Date
String token = JWT.create().withClaim(“useId”,userId.toString()).withClaim(“createdAt”, new Date()).sign(algorithm)

// return token 
}

########################################################################################################################

VERIFYING TOKEN

getUserIDByToken(String token){

algorithm.HMAC256(TOKEN_SECRET)

// Verifying the the algorithm with jwtverifier
JWTVerifier  verifier = JWT.require(algorithm).build()

// Token decoding using the verifier build on the HMAC256 algorithm
// DecodedJWT jwt = verifier.verify(token)

return jwt.getClaim(“userId”).asString()
}

########################################################################################################################

@Configuration // Bean extends GenericFilterBean Filter => JWTFilter => @Autowiring => tokenService

Spring Application on what routes the it has to be applied the authorization[token]
and also excluding some routes Which doesn’t need your token requirement
doFilter(ServletReq req, Servlet res, FilterChain filterChain) => HttpServletrequest request = (HttpServletRequest) req => HttpServletresponse response = (HttpServletResponse) res Configuring to Http Protocol
If authorization token exists assigning it to the String of token => String token = request.getHeader(“Authorization”)

if(allowRequestWithoutToken(request)){ response.setStatus(HttpServletResponse.SC_OK)=> 200 } else{ Object userId = new ObjectId(tokenService.getUserIdFromToken(token)) request.setAttirbute(“userId”, userId); filterChain.doFilter(req, res) }

########################################################################################################################










