# es-language-char-filter
A elasticsearch char filter for dividing multi-language to different fields.

## Introduction
Some analyzers of Elasticsearch are based on language such as __english__ analyzer. They tokenize documents into terms according to the specific grammars. 

However, the analyzers will not bypass the foreign language. When tacking multi-language documents, there is a recommaned solution that creating sub-fields which use the special analyzer depending on its language. The foreign lanuage would impact the accurate and efficiency of search.

For instance,
```json
POST _analyze
{
  "analyzer": "english",
  "text": "We are going to meet at 中山路."
}
```
The generated terms are,
```javascript
  [ "we", "go", "meet", "中", "山", "路" ]
```
"中山路" is actually a road name and it is divided into independent charaters.

If we switch to Chinese analyzer, for instance, IK, it tokenizes Chinese characters correctly but keep English words,
```json
POST _analyze
{
  "analyzer": "ik_smart",
  "text": "We are going to meet at 中山路."
}
```

The generated terms are,
```javascript
 ["we", "going", "meet", "中山路"]
```

This time, Chinese characters tokenized correctly, but it also kept English word.

Both of these cases will leed to search issues because the matching score covers all the fields.

This char filter is aiming at filtering languages to make one filed only storing one language terms.

## Usage
