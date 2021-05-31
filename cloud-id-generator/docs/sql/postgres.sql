create table "cloud-id-generator".worker_node
(
    id          serial      not null
        constraint worker_node_pk
            primary key,
    host_name   varchar(64) not null,
    port        varchar(64) not null,
    type        integer     not null,
    launch_date date        not null,
    modified    timestamp   not null,
    created     timestamp   not null
);

comment
on table "cloud-id-generator".worker_node is 'DB WorkerID Assigner for UID Generator';

comment
on column "cloud-id-generator".worker_node.host_name is 'host name';

comment
on column "cloud-id-generator".worker_node.port is 'port';

comment
on column "cloud-id-generator".worker_node.type is 'node type: ACTUAL or CONTAINER';

comment
on column "cloud-id-generator".worker_node.launch_date is 'launch date';

comment
on column "cloud-id-generator".worker_node.modified is 'modified time';

comment
on column "cloud-id-generator".worker_node.created is 'created time';

alter table "cloud-id-generator".worker_node owner to postgres;

create
unique index worker_node_id_uindex
	on "cloud-id-generator".worker_node (id);

