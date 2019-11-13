

https://clmirror.storage.googleapis.com/codelabs/android-room-with-a-view/index.html?index=..%2F..index#4

implementation "androidx.room:room-runtime:$room_version"
annotationProcessor "androidx.room:room-compiler:$room_version"

1.
@Entity(tableName = "word_table")
每个@Entity类表示表中的一个实体。注释类声明以指示它是一个实体。如果希望表名与类名不同，请指定表名。

@PrimaryKey
每个实体都需要一个主键。为了保持简单，每个单词都充当自己的主键。

@NonNull
表示参数、字段或方法返回值永远不能为空。

@ColumnInfo(name = "word")
如果希望表中的列与成员变量的名称不同，请指定该列的名称。

存储在数据库中的每个字段都需要是公共的或具有“getter”方法。此示例提供getWord（）方法


2.
在DAO（数据访问对象）中，指定SQL查询并将它们与方法调用相关联。编译器检查SQL并从方便注释中为常见查询（如@Insert）生成查询。
DAO必须是接口或抽象类。


3.
插入数据时，可以提供冲突策略。
在此代码实验室中，您不需要冲突策略，因为单词是您的主键，默认的SQL行为是ABORT，因此您不能将具有相同主键的两个项目插入数据库。
@Insert(onConflict = OnConflictStrategy.REPLACE)

ROLLBACK
当发生约束冲突，立即ROLLBACK，即结束当前事务处理，命令中止并返回SQLITE_CONSTRAINT代码。若当前无活动事务(除了每一条命令创建的默认事务以外)，则该算法与ABORT相同。

ABORT
当发生约束冲突，命令收回已经引起的改变并中止返回SQLITE_CONSTRAINT。但由于不执行ROLLBACK，所以前面的命令产生的改变将予以保留。缺省采用这一行为。

FAIL
当发生约束冲突，命令中止返回SQLITE_CONSTRAINT。但遇到冲突之前的所有改变将被保留。例如，若一条UPDATE语句在100行遇到冲突100th，前99行的改变将被保留，而对100行或以后的改变将不会发生。

IGNORE
当发生约束冲突，发生冲突的行将不会被插入或改变。但命令将照常执行。在冲突行之前或之后的行将被正常的插入和改变，且不返回错误信息。

REPLACE
当发生UNIQUE约束冲突，先存在的，导致冲突的行在更改或插入发生冲突的行之前被删除。这样，更改和插入总是被执行。命令照常执行且不返回错误信息。当发生NOT NULL约束冲突，导致冲突的NULL值会被字段缺省值取代。若字段无缺省值，执行ABORT算法。

当冲突应对策略为满足约束而删除行时，它不会调用删除触发器。但在新版中这一特性可能被改变。


4.
您的Room类必须是抽象类，并且必须扩展RoomDatabase。
通常，整个应用程序只需要一个Room数据库实例。

5。
在Room中，对于 insert ,update, query 需要使用后台线程，否则就会报错：java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
查询有liveData 所以除了查询不需要 其它都需要加线程

WordRepository

    public void delete(Word word) {
        new DeleteAsyncTask(mWordDao).execute(word);
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }