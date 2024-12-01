export function parseDate(localDateTimeString) {
  const date = new Date(localDateTimeString);

  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

  const month = months[date.getMonth()];
  const day = date.getDate();
  const year = date.getFullYear();

  return `${month}, ${day}, ${year}`;
}

export function formatHandle(username) {
    return `@${username.toLowerCase().split(" ").join("_")}`
  }