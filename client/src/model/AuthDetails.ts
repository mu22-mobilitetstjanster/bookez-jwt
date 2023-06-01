export interface SigninAuthDetails {
  username: string;
  password: string;
}

export interface SignupAuthDetails extends SigninAuthDetails {}