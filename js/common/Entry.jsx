export default function Entry(props) {
  const formType = props.formType.toUpperCase();

  return (
    <div className="entry-form">
      <h2 className="form-type">{formType}</h2>
      <div className="form-container">{props.form}</div>
      <p className="switch-form-txt">{formType === "LOGIN" ? "Haven't signed up yet?" : "Already have an account?"}</p>
      <button onClick={() => props.navigateTo(formType === "LOGIN" ? props.signUpUrl : props.loginUrl)} className="switch-form-btn">
        {formType === "LOGIN" ? "Sign Up" : "Log In"}
      </button>
    </div>
  )
}