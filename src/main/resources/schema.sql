/**
 * Author:  Richard Fellinger rich@richfell.org
 * Created: Nov 14, 2017
 */

create table if not exists sayings (
    id integer not null identity,
    name varchar(64) not null,
    quote varchar(256) not null
);
