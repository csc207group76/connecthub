import { createRoot } from "react-dom/client"
import CreatePost from "./CreatePost.jsx"

const container = document.getElementById("createPostDiv")
const root = createRoot(container)
root.render(<CreatePost />)
