create table DynamicStorageAppEntity (
	appEntityId LONG not null primary key,
	appId LONG,
	entityName VARCHAR(75) null,
	DDMStructureId LONG
);