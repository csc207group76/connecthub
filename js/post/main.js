import { createRoot } from "react-dom/client"
import Post from "./Post.jsx"

const container = document.getElementById("postDiv")
const root = createRoot(container)
root.render(<Post />)