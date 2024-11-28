import { createRoot } from "react-dom/client"
import HomePage from "./HomePage.jsx"

const container = document.getElementById("homeDiv")
const root = createRoot(container)
root.render(<HomePage />)
