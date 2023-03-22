export const findThumbnail = (s) => {
  const re = /<img\s+src\s*=\s*"(.*?)"/;
  const result = re.exec(s);
  return result !== null ? result[1] : null;
};
