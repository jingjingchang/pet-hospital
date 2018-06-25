package com.zcmzjp.wx.entity;

/**
 * 文件
 * 
 * @author yangjunping
 * 
 */
public class FileInfo {
	/**
	 * 文件签名
	 */
	private String md5;

	/**
	 * 分片序号
	 */
	private int chunkIndex;

	/**
	 * 文件大小
	 */
	private String size;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 分片数
	 */
	private int chunks;

	/**
	 * 当前分片
	 */
	private int chunk;

	/**
	 * 最后修改时间
	 */
	private String lastModifiedDate;

	/**
	 * application/x-msdownload
	 */
	private String type;

	/**
	 * 文件扩展名
	 */
	private String ext;

	/**
	 * 上传文件存放路径
	 */
	private String path;

	/**
	 * 文件唯一标识
	 */
	private String uniqueCode;

	/**
	 * 上档文件存储实例
	 */
	private String clazz;

	/**
	 * 节点ID
	 */
	private String resourceId;

	public FileInfo() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getChunkIndex() {
		return chunkIndex;
	}

	public void setChunkIndex(int chunkIndex) {
		this.chunkIndex = chunkIndex;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return "name=" + this.name + "; size=" + this.size + "; chunkIndex="
				+ this.chunkIndex + "; md5=" + this.md5 + "; userId="
				+ "; chunks=" + this.chunks + "; chunk=" + this.chunk
				+ "; uniqueCode=" + this.uniqueCode + "; lastModifiedDate="
				+ this.lastModifiedDate + "; type=" + this.type + "; ext="
				+ this.ext;
	}
}
