import { createRoot } from "react-dom/client"
import Login from "./Login.jsx"

const container = document.getElementById("loginDiv")
const root = createRoot(container)
root.render(<Login />)
