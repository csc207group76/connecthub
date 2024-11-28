import { useEffect, useState } from "react"

export default function Topics(props) {
  const [displayedTopics, setDisplayedTopics] = useState([])

  useEffect(() => {
    const topics = []

    props.posts.reduce((curMap, post) => {
      const category = post.category
      return curMap.set(category, curMap.has(category) ? curMap.get(category) + 1 : 1)
    }, new Map()).forEach((value, key) => {
      topics.push({ "topic": key, "numPosts": value })
    });

    setDisplayedTopics(topics)
  }, [props.posts])

  return (
    <div id="topics-container">
      <div className="section-title">BROWSE TOPICS</div>
      {displayedTopics.map((topicEntry, index) => {
        return <TopicEntry
          key={index}
          topic={topicEntry.topic}
          numPosts={topicEntry.numPosts}
        />
      })}
    </div>
  )
}

function TopicEntry(props) {
  return (
    <div className="topic-entry">
      <div className="topic-name">{props.topic}</div>
      <div className="topic-num-posts">{props.numPosts}</div>
    </div>
  )
}