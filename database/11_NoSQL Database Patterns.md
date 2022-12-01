# NoSQL Database Patterns

>  Not Only SQL

- RDBMS와 구분되는 Database
- 기존의 RDBMS에서 새로운 요구사항이 많아지면서 하나의 시스템이 감당하기 힘들어짐

- XML, JSON 등의 semi-structured data를 분석하기 위해 NoSQL이 등장



### NoSQL 종류

- Key-Value Stores
  - riak
  - redis
  - dynamoDB
- Document Stores
  - mongoDB
  - Couchbase
  - ravenDB
- Column-Family Stores
  - Cassandra



### Relational DB

- Data is usually stored in row by row manner (row store)
- Standardized query language(SQL)
- Data model defined before you add data
- joins merge data from multiple tables
- results are tables
- Pros: mature ACID taransactions with fine-grain security controls
- Cons: requires up front data modeling, does not scale well
- Examples  : Oracle, MySQL, PostgreSQL, MS SQL



### Analytical (OLAP)

- based on "Star" schema with central fact table for each event
- Optimized for analysis of read-analysis of historical data
- use of MDX language to count query 'measures ' for 'categories' of data
- Pros : fast queries for large data
- cons : not optimized for transactions and updates
- examples : Cognos, Hyperion, Microstrategy, MS, Oracle



### Key-Value Stores

- keys used to access opaque blobs of data
- values can contain any type of data (images, video)
- pros : scalable, simple API(put, get, delete)
- cons: no way to query based on the content of the value
- examples : berkeley DB, Memcache, DynamoDB, S3, Redis, Riak



### Column-Family

- Key includes a row, column family and column name
- Store versioned blobs in one large table
- Queries can be done on rows, column families and column names
- Pros: good sacle out, versioning
- Cons: cannot query blob content, row and column designs are critical
- Examples : Cassandra, HBase, Hypertable, Apache Accumulo, Bigtable



### Column Store Concept

- Preserve the table-structure familiar to RDBMS systems
- Not optimized for "joins"
- One row could have millions of columns but the data can be very "sparse"
- Ideal for high-variability data sets
- Column families allow to query all columns that have a specific property 
- Allow new columns to be inserted without doing an 'alter table'
- trigger new columns on inserts



### Column families

- group columns into 'column familes'
- group columns families into 'super-columns'
- be able to query all columns with a family or super family
- similar data grouped together to improve speed
