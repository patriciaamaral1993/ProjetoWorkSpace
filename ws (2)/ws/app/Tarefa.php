<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Tarefa extends Model
{
  protected $table = "tarefa";
  protected $primaryKey = "id";
  protected $fillable = array("nome", "descricao", "data");
  public $timestamps = false;
}