import { createRoot } from "react-dom/client"
import SignUp from "./SignUp.jsx"

const container = document.getElementById("signUpDiv")
const root = createRoot(container)
root.render(<SignUp />)
