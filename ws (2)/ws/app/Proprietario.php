<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class proprietario extends Model
{
  protected $table = "proprietario";
  protected $primaryKey = "id";
  protected $fillable = array("nome", "ano de nascimento");
  public $timestamps = false;
}